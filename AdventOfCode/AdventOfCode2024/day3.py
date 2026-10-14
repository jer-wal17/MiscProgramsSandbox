import re

matchString = r"do\(\)|don't\(\)|mul\(\d{1,3},\d{1,3}\)"

sumOfMatches = 0

enabled = True

with open("InputFiles/Day3.txt", "r") as file:
    for line in file:
        matches = re.findall(matchString, line.strip())
        for match in matches:
            if (match[0] == "d"):
                if match == "do()":
                    enabled = True
                if match == "don't()":
                    enabled = False
            else:
                if enabled:
                    numbersStr = re.search(r"\((.*?)\)", match)
                    numbers = numbersStr.group(1)
                    numbers = numbers.split(",")
                    numbers = [numStr.strip() for numStr in numbers]
                    sumOfMatches += int(numbers[0]) * int(numbers[1])

print(sumOfMatches)
