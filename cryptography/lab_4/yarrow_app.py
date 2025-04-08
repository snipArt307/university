import hashlib
from Cryptodome.Cipher import DES  # Исправлено на Cryptodome
from Cryptodome.Random import get_random_bytes  # Исправлено на Cryptodome
import time
import os
import math
import tkinter as tk
from tkinter import ttk, messagebox, scrolledtext
import json

class Yarrow160:
    def __init__(self, key_size=64, key_update_interval=160, entropy_update_interval=160):
        self.key_size = key_size
        self.key_update_interval = key_update_interval
        self.entropy_update_interval = entropy_update_interval
        self.counter = 0
        self.key = get_random_bytes(key_size // 8)
        self.entropy_accumulator = b""
        self.key_update_count = 0

    def _des_encrypt(self, data):
        cipher = DES.new(self.key, DES.MODE_ECB)
        return cipher.encrypt(data.ljust(8, b'\0'))

    def _sha1_hash(self, data):
        sha1 = hashlib.sha1()
        sha1.update(data)
        return sha1.digest()

    def _update_entropy_accumulator(self):
        entropy_data = str(time.time()).encode() + str(os.getpid()).encode()
        self.entropy_accumulator += entropy_data
        if len(self.entropy_accumulator) > self.entropy_update_interval // 8:
            self.entropy_accumulator = self.entropy_accumulator[-self.entropy_update_interval // 8:]

    def _update_key_and_counter(self):
        self.key = self._sha1_hash(self.entropy_accumulator + self.key)
        self.counter = int.from_bytes(self._des_encrypt(b"\x00" * 8), 'big') % 2**64
        self.key_update_count += 1

    def generate_random_bytes(self, n_bytes):
        result = b""
        while len(result) < n_bytes:
            self._update_entropy_accumulator()
            if self.key_update_count * self.key_update_interval >= len(self.entropy_accumulator) * 8:
                self._update_key_and_counter()
            encrypted_data = self._des_encrypt(self.key)
            result += encrypted_data
        return result[:n_bytes]

def bytes_to_bits(byte_data):
    bits = []
    for byte in byte_data:
        for i in range(7, -1, -1):
            bits.append((byte >> i) & 1)
    return bits

def frequency_test(bits):
    n = len(bits)
    X = [2 * bit - 1 for bit in bits]
    S_n = sum(X)
    S = abs(S_n) / math.sqrt(n)
    threshold = 1.82138636
    passed = S <= threshold
    return {
        "passed": passed,
        "details": f"Сумма S_n = {S_n}\nСтатистика S = {S}\nПорог = {threshold}\nРезультат: {'Пройден' if passed else 'Не пройден'}"
    }

def runs_test(bits):
    n = len(bits)
    pi = sum(bits) / n
    V_n = 1 + sum(1 if bits[i] != bits[i + 1] else 0 for i in range(n - 1))
    denominator = 2 * math.sqrt(2 * n) * pi * (1 - pi)
    S = abs(V_n - 2 * n * pi * (1 - pi)) / denominator if denominator != 0 else float('inf')
    threshold = 1.82138636
    passed = S <= threshold
    return {
        "passed": passed,
        "details": f"Доля единиц pi = {pi}\nКоличество смен V_n = {V_n}\nСтатистика S = {S}\nПорог = {threshold}\nРезультат: {'Пройден' if passed else 'Не пройден'}"
    }

def cumulative_sums_test(bits):
    n = len(bits)
    X = [2 * bit - 1 for bit in bits]
    S = [0] * (n + 2)
    for i in range(n):
        S[i + 1] = S[i] + X[i]
    S[n + 1] = 0
    
    k = S.count(0)
    L = k - 1
    states = range(-9, 10)
    xi = {j: S.count(j) for j in states if j != 0}
    
    results = {}
    threshold = 1.82138636
    all_passed = True
    details = [f"Количество нулей L = {L}"]
    for j in states:
        if j == 0:
            continue
        xi_j = xi.get(j, 0)
        Y_j = abs(xi_j - L) / math.sqrt(2 * L * (4 * abs(j) - 2)) if L > 0 else float('inf')
        passed = Y_j <= threshold
        results[j] = (Y_j, passed)
        details.append(f"Состояние {j}: Y_j = {Y_j}, {'Пройден' if passed else 'Не пройден'}")
        if not passed:
            all_passed = False
    
    details.append(f"Порог = {threshold}")
    details.append(f"Общий результат: {'Пройден' if all_passed else 'Не пройден'}")
    return {
        "passed": all_passed,
        "details": "\n".join(details)
    }

class YarrowApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Генератор и тестер Yarrow-160")
        self.root.geometry("800x600")

        # Поля ввода параметров
        ttk.Label(root, text="Размер ключа (бит):").grid(row=0, column=0, padx=5, pady=5)
        self.key_size_entry = ttk.Entry(root)
        self.key_size_entry.insert(0, "64")
        self.key_size_entry.grid(row=0, column=1, padx=5, pady=5)

        ttk.Label(root, text="Интервал обновления ключа (бит):").grid(row=1, column=0, padx=5, pady=5)
        self.key_interval_entry = ttk.Entry(root)
        self.key_interval_entry.insert(0, "160")
        self.key_interval_entry.grid(row=1, column=1, padx=5, pady=5)

        ttk.Label(root, text="Интервал обновления энтропии (бит):").grid(row=2, column=0, padx=5, pady=5)
        self.entropy_interval_entry = ttk.Entry(root)
        self.entropy_interval_entry.insert(0, "160")
        self.entropy_interval_entry.grid(row=2, column=1, padx=5, pady=5)

        ttk.Label(root, text="Количество бит для генерации:").grid(row=3, column=0, padx=5, pady=5)
        self.bits_entry = ttk.Entry(root)
        self.bits_entry.insert(0, "10000")
        self.bits_entry.grid(row=3, column=1, padx=5, pady=5)

        # Кнопки
        ttk.Button(root, text="Загрузить параметры из файла", command=self.load_parameters).grid(row=4, column=0, padx=5, pady=5)
        ttk.Button(root, text="Сгенерировать последовательность", command=self.generate_sequence).grid(row=4, column=1, padx=5, pady=5)
        ttk.Button(root, text="Запустить тесты", command=self.run_tests).grid(row=4, column=2, padx=5, pady=5)

        # Текстовое поле для вывода результатов
        self.result_text = scrolledtext.ScrolledText(root, width=90, height=30)
        self.result_text.grid(row=5, column=0, columnspan=3, padx=5, pady=5)

        self.bits = None

    def load_parameters(self):
        try:
            with open("params.json", "r") as f:
                params = json.load(f)
                self.key_size_entry.delete(0, tk.END)
                self.key_size_entry.insert(0, str(params.get("key_size", 64)))
                self.key_interval_entry.delete(0, tk.END)
                self.key_interval_entry.insert(0, str(params.get("key_update_interval", 160)))
                self.entropy_interval_entry.delete(0, tk.END)
                self.entropy_interval_entry.insert(0, str(params.get("entropy_update_interval", 160)))
                self.bits_entry.delete(0, tk.END)
                self.bits_entry.insert(0, str(params.get("bits_to_generate", 10000)))
            self.result_text.insert(tk.END, "Параметры загружены из файла params.json\n")
        except Exception as e:
            messagebox.showerror("Ошибка", f"Не удалось загрузить параметры: {e}")

    def generate_sequence(self):
        try:
            key_size = int(self.key_size_entry.get())
            key_update_interval = int(self.key_interval_entry.get())
            entropy_update_interval = int(self.entropy_interval_entry.get())
            bits_to_generate = int(self.bits_entry.get())

            if key_size % 8 != 0:
                raise ValueError("Размер ключа должен быть кратен 8")

            yarrow = Yarrow160(key_size, key_update_interval, entropy_update_interval)
            n_bytes = (bits_to_generate + 7) // 8  # округляем вверх до полного байта
            random_data = yarrow.generate_random_bytes(n_bytes)
            self.bits = bytes_to_bits(random_data)[:bits_to_generate]  # обрезаем до нужного количества бит

            # Сохраняем последовательность в файл
            with open("random_sequence.txt", "w") as f:
                f.write("".join(map(str, self.bits)))

            self.result_text.delete(1.0, tk.END)
            self.result_text.insert(tk.END, f"Сгенерировано {len(self.bits)} бит\n")
            self.result_text.insert(tk.END, f"Последовательность сохранена в random_sequence.txt\n")
        except Exception as e:
            messagebox.showerror("Ошибка", f"Не удалось сгенерировать последовательность: {e}")

    def run_tests(self):
        if self.bits is None:
            messagebox.showwarning("Предупреждение", "Сначала сгенерируйте последовательность!")
            return

        self.result_text.delete(1.0, tk.END)
        self.result_text.insert(tk.END, f"Запуск тестов для {len(self.bits)} бит...\n\n")

        # Частотный тест
        freq_result = frequency_test(self.bits)
        self.result_text.insert(tk.END, "Частотный тест:\n")
        self.result_text.insert(tk.END, freq_result["details"] + "\n\n")

        # Тест на последовательность одинаковых бит
        runs_result = runs_test(self.bits)
        self.result_text.insert(tk.END, "Тест на последовательность одинаковых бит:\n")
        self.result_text.insert(tk.END, runs_result["details"] + "\n\n")

        # Расширенный тест на произвольные отклонения
        cum_result = cumulative_sums_test(self.bits)
        self.result_text.insert(tk.END, "Расширенный тест на произ rouges отклонения:\n")
        self.result_text.insert(tk.END, cum_result["details"] + "\n")

if __name__ == "__main__":
    root = tk.Tk()
    app = YarrowApp(root)
    root.mainloop()
