numDigits: int = int(input("Enter number of digits in code: "))

numTotalPossibleSolutions: int = 10**numDigits

print("Total possible solutions:", numTotalPossibleSolutions)

USER_PROMPT_MESSAGE: str = "Enter a guess (Q/q to quit): "


def getUserInput() -> str:
    return str(input(USER_PROMPT_MESSAGE))


def generateAllPossibleSolutions() -> list[str]:
    possSols: list[str] = []
    for i in range(1, numTotalPossibleSolutions + 1):
        possSols.append(str(i).zfill(numDigits))

    return possSols


def printFirstXPossibleSolutions(x: int) -> None:
    for i in range(x):
        print(allPossibleSolutions[i], end=" ")
    print()


allPossibleSolutions: list[str] = generateAllPossibleSolutions()

userInput: str = getUserInput()
while userInput.lower() != "Q".lower():
    if len(userInput) != numDigits:
        print("Invalid number of digits.")
        userInput = getUserInput()
        continue

    paddedUserInput: str = userInput.zfill(numDigits)

    if paddedUserInput not in allPossibleSolutions:
        print("Already guessed.")
        userInput = getUserInput()
        continue

    allPossibleSolutions.remove(paddedUserInput)

    userInput = getUserInput()
