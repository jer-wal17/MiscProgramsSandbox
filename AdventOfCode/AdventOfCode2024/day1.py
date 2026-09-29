from collections import Counter

leftList = []
rightList = []
numSpaces = 3

with open("InputFiles/Day1.txt", "r") as file:
    for line in file:
        leftAndRightNum = line.strip().split("   ")
        leftList.append(leftAndRightNum[0])
        rightList.append(leftAndRightNum[1])

rightListCounts = Counter(rightList)

similarityScore = 0

for number in leftList:
    similarityScore += int(number) * int(rightListCounts[number])

print(similarityScore)
