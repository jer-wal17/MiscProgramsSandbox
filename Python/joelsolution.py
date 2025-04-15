def checker(num):
    x = num
    while num >= 1:
        num = num / 2
        if x == 1:
            return True
        x = num
    return False


print(checker(63821763))
