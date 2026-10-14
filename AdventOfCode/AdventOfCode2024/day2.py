from itertools import pairwise

reports = []

with open("InputFiles/Day2.txt", "r") as file:
    for line in file:
        reports.append(line.strip())

reportSafeOrUnsafe = []

def determineIfSafe(numbers, recursed=False):
    if numbers != sorted(numbers) and numbers != sorted(numbers, reverse=True):
        if recursed:
            return False
        for i, num in enumerate(numbers):
            if determineIfSafe(numbers[:i] + numbers[i + 1:], recursed=True):
                return True
        return False

    for current_num, next_num in pairwise(numbers):
        difference = abs(current_num - next_num)
        if difference < 1 or difference > 3:
            if recursed:
                return False
            for i, num in enumerate(numbers):
                if determineIfSafe(numbers[:i] + numbers[i + 1:], recursed=True):
                    return True
            return False

    return True

for report in reports:
    numbersStr = report.split(" ")
    numbers = [int(numStr) for numStr in numbersStr]
    reportSafeOrUnsafe.append(str(determineIfSafe(numbers)))

print(reportSafeOrUnsafe.count("True"))
