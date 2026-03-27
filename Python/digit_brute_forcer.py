import re

numDigits: int = int(input("Enter number of digits in code: "))

numTotalPossibleSolutions: int = 10**numDigits

USER_PROMPT_MESSAGE: str = "Enter a guess (D/d to enter known digit(s), Q/q to quit): "


def getUserInput(prompt: str = USER_PROMPT_MESSAGE) -> str:
    print("Remaining possible solutions:", len(allPossibleSolutions))
    print("Known digits:", knownCombo)
    return str(input(prompt))


def continueThroughLoop(infomessage: str):
    print(infomessage)
    return getUserInput()


def generateAllPossibleSolutions() -> list[str]:
    possSols: list[str] = []
    for i in range(numTotalPossibleSolutions):
        possSols.append(str(i).zfill(numDigits))

    return possSols


def printFirstXPossibleSolutions(x: int) -> None:
    for i in range(x):
        print(allPossibleSolutions[i], end=" ")
    print()


def keepKnownDigitCombos() -> tuple[list[str], str]:
    knownDigts: str = str(
        input("Enter known digits with 'x' in place of unknowns: "))
    knownCombo = knownDigts.lower().replace("x", "_")
    regexString: str = knownDigts.lower().replace("x", ".")
    regex = re.compile(regexString)
    return list(filter(regex.match, allPossibleSolutions)), knownCombo


allPossibleSolutions: list[str] = generateAllPossibleSolutions()

knownCombo: str = "___"

userInput: str = getUserInput()
while userInput.lower() != "Q".lower():

    if userInput.lower() == "D".lower():
        allPossibleSolutions, knownCombo = keepKnownDigitCombos()
        userInput = getUserInput()
        continue

    if len(userInput) != numDigits:
        userInput = continueThroughLoop("Invalid number of digits.")
        continue

    paddedUserInput: str = userInput.zfill(numDigits)

    if paddedUserInput not in allPossibleSolutions:
        userInput = continueThroughLoop("Already guessed.")
        continue

    allPossibleSolutions.remove(paddedUserInput)

    userInput = getUserInput()
