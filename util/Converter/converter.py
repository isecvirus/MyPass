# This file is a tool to convert password file for MyPass software.
# from version 3.2.0 and below, two examples for <3.2.0 and 4.2.0.
#
# WARNING: Passwords must get presented in plaintext format..
# Decrypt it using your Private RSA Key and save it totally as plaintext.
#
# <3.2.0v
# {
#     "google": "l1^{ghSi$4PG4%g:"
# }
#
#
# 4.2.0v
# {
#     "google": {
#         "username": "mail@gmail.com",
#         "url": "https://www.google.com/accounts",
#         "password": "^%-4_)<B<dhtQ8b7",
#         "note": "I need to change my password, since the old one got compromised.",
#         "created": "2023-03-23 11:43:34",
#         "modified": "2023-03-27 19:38:12"
# }
#
# Usage:
#   python3 converter.py <filename>.json
# Result:
#   A file with the same <filename>.mp will be generated..
#   Also should provide a AES-256 password for the encryption process.
import base64
import json, sys, binascii
from Crypto.Cipher import AES # pip3 install pycryptodome
from Crypto.Util.Padding import pad, unpad

password = "\x00" * 16 # key
salt = b'\x00' * 18
iv = b'\x00' * 16


data = base64.b64encode(open("../../../../../../../../test.mp", "rb").read())
iv = data[:16]


from hashlib import sha256
import base64
from Crypto import Random
from Crypto.Cipher import AES

BS = 16
pad = lambda s: bytes(s + (BS - len(s) % BS) * chr(BS - len(s) % BS), 'utf-8')
unpad = lambda s : s[0:-ord(s[-1:])]

class AESCipher:

    def __init__( self, key ):
        self.key = bytes(key, 'utf-8')

    def encrypt( self, raw ):
        raw = pad(raw)
        iv = Random.new().read( AES.block_size )
        cipher = AES.new(self.key, AES.MODE_CBC, iv )
        return base64.b64encode( iv + cipher.encrypt( raw ) )

    def decrypt( self, enc ):
        enc = base64.b64decode(enc)
        iv = enc[:16]
        cipher = AES.new(self.key, AES.MODE_CBC, iv )
        return unpad(cipher.decrypt( enc[16:] )).decode('utf8')

cipher = AESCipher('mysecretpassword')
# encrypted = cipher.encrypt('Secret')
decrypted = cipher.decrypt(base64.b64encode(open("../../../../../../../../test.mp", "rb").read()))

print(decrypted)

# file = sys.argv[1] # file name