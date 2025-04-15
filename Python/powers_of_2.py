userNum = int(input("Enter a number to check if it's a power of 2: "))
currPower = 1
while currPower < userNum:
    currPower *= 2

if currPower == userNum:
    print("Your number is a power of 2")
else:
    print("Your number is not a power of 2")
