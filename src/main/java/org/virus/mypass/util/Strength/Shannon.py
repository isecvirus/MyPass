import math
from collections import defaultdict

"""
H = -Σ(Pi * log2(Pi))

Entropy for 'Hello, World!' is 3.180832987205441:
    CyberChef
        https://gchq.github.io/CyberChef/#recipe=Entropy('Shannon%20scale')&input=SGVsbG8sIFdvcmxkIQ
    Dcode.fr
        https://www.dcode.fr/shannon-index 
"""

def passwordStrength(password):
    strength = 0
    ent = entropy(password)
    lengths = {range(i, i+2): 2*i for i in range(0, 50, 2)}
    multipliers = {
        2.0: 0.5,
        3.0: 0.8,
        4.0: 0.9,
        5.0: 0.95,
        6.0: 0.97,
        7.0: 0.98,
        8.0: 0.99
    }

    upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    lower = "abcdefghijklmnopqrstuvwxyz"
    nums = "0123456789"
    puncs = "~`!@#$%^&*()_+=-{[]}\\|\"';:/?.>,<"
    lower_score = 2
    upper_score = 4
    nums_score = 6
    puncs_score = 8

    strength += sum([
        sum([lower_score if (char in lower) else 0 for char in password]),
        sum([upper_score if (char in upper) else 0 for char in password]),
        sum([nums_score if (char in nums) else 0 for char in password]),
        sum([puncs_score if (char in puncs) else 0 for char in password]),
    ])


    strength += sum(lengths[l] for l in lengths if len(password) in l)

    for value, multiplier in multipliers.items():
        if ent < value:
            strength = int(strength * multiplier)
            break

    return min(strength, 100)

def entropy(password: str, rounded: int = 16) -> float:
    charCount = defaultdict(int)
    entropy = 0.0

    for c in password:
        charCount[c] += 1

    for count in charCount.values():
        freq = count / len(password)
        entropy -= freq * (math.log(freq) / math.log(2)) # log(2)=0.6931471805599453

    return round(entropy, rounded)