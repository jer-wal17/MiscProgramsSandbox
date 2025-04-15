input = input("Enter a string to check for \u03C9c\u03C9: ")
split_input = input.split("c")
if split_input[0] == split_input[1]:
    print("String matches form")
else:
    print("String does not match form")
