number = int(input())
time = map(int, input().split())
sort = sorted(time)

result = 0
for i in sort:
    result += i * number
    number -= 1

print(result)
