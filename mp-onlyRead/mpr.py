import json
from tkinter import Tk, Menu
from tkinter.filedialog import askopenfilename
from tkinter.messagebox import showerror
from tkinter.scrolledtext import ScrolledText
from tkinter.simpledialog import askstring
from tkinter.ttk import *

from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC

# {
#   'username': ''
#   'password': '',
#   'url': '',
#   'note': '',
#   'passwordHistory': [],
# }

default_salt = b'\x00' * 18
default_iv = b'\x00' * 16

class AES:
    def __init__(self, password:str, salt:bytes=default_salt, iv:bytes=default_iv):
        self.password = password.encode()
        self.salt = salt
        self.iv = iv
        self.backend = default_backend()

    def encrypt(self, plaintext):
        cbc = modes.CBC(initialization_vector=self.iv)

        kdf = PBKDF2HMAC(
            algorithm=hashes.SHA256(),
            length=32,
            salt=self.salt,
            iterations=65536,
            backend=default_backend()
        )
        key = kdf.derive(self.password)

        cipher = Cipher(algorithms.AES(key), cbc, backend=self.backend)
        encryptor = cipher.encryptor()
        ciphertext = encryptor.update(plaintext.encode()) + encryptor.finalize()
        return ciphertext

    def decrypt(self, ciphertext):
        cbc = modes.CBC(initialization_vector=self.iv)

        kdf = PBKDF2HMAC(
            algorithm=hashes.SHA256(),
            length=32,
            salt=self.salt,
            iterations=65536,
            backend=default_backend()
        )
        key = kdf.derive(self.password)

        cipher = Cipher(algorithms.AES(key), cbc, backend=self.backend)
        decryptor = cipher.decryptor()
        plaintext = decryptor.update(ciphertext) + decryptor.finalize()
        return plaintext

    def mypass2json(self, file:str):
        ciphertext = open(file=file, mode="rb").read()

        decrypted_data = self.decrypt(ciphertext)
        data = decrypted_data.decode(encoding="utf-8", errors="ignore").rstrip("\x05")

        return json.loads(data)


columns = ("Entity", "Created", "Modified", "History")

window = Tk()
window.title("MyPass (Read only)")

style = Style()
style.configure('Treeview', rowheight=50)

menu = Menu(window)
file_menu = Menu(menu, tearoff=False)
menu.add_cascade(label="File", menu=file_menu)

entities = {}

def open_mp():
    global entities
    filename = askopenfilename(title="Select file to read", filetypes=(("MyPass", "*.mp"), ("MyPass", "*.mypass")))
    if filename:
        while True:
            password = askstring(parent=window, title="Password", prompt="Enter the password:", show="•")

            if password == None: break

            try:
                entities = AES(password=password).mypass2json(file=filename)

                if "entities" in entities:
                    entities = entities['entities']
                else:
                    entities = entities

                for entity in sorted(entities):
                    created = entities[entity]['created']
                    modified = entities[entity]['modified']
                    passwordsHistory = set(entities[entity]['passwordHistory'] + [entities[entity]['password']])

                    table.insert('', 'end', values=(entity, created, modified, len(passwordsHistory)))

                break
            except json.decoder.JSONDecodeError:
                showerror(title="Error!", message="Incorrect password")
                continue
            except Exception as error:
                showerror(title="Error!", message=error.__str__())

file_menu.add_command(label="open", command=lambda :open_mp())

table_frame = Frame(window)
table_frame.pack(side='top', expand=True, fill='both')

table = Treeview(table_frame, takefocus=False, selectmode="extended", columns=columns, show="headings")

def change_selection(event):
    clicked = table.selection()
    if clicked:
        entity = table.item(clicked[0])['values'][0]
        username = entities[entity]['username']
        url = entities[entity]['url']
        password = entities[entity]['password']
        passwords_history = list(set(entities[entity]['passwordHistory'] + [password]))
        note = entities[entity]['note']

        username_entry.delete(0, 'end')
        username_entry.insert(0, username)

        url_entry.delete(0, 'end')
        url_entry.insert(0, url)

        password_entry.delete(0, 'end')
        password_entry.insert(0, password)

        passwords_history_entry.delete(0, 'end')
        passwords_history_entry.config(values=passwords_history)
        passwords_history_entry.current(0)

        note_box.delete(1.0, 'end')
        note_box.insert(1.0, note)

table.bind("<<TreeviewSelect>>", change_selection)

table_y = Scrollbar(table_frame, orient="vertical", command=table.yview)
table_y.pack(side='right', fill='y', pady=(0, 15))

table_x = Scrollbar(table_frame, orient="horizontal", command=table.xview)
table_x.pack(side='bottom', fill='x')

table.config(xscrollcommand=table_x.set, yscrollcommand=table_y.set)
table.pack(expand=True, fill='both')

note_box = ScrolledText(window, width=25, height=5, wrap="word", undo=True, maxundo=-1, tabstyle="word")
note_box.pack(expand=True, fill='both', side='bottom', padx=10, pady=(0, 10))
Label(window, text="Note:").pack(anchor='w', side='bottom', padx=(10, 0))

info_frame = Frame(window)
info_frame.pack(side='bottom', fill='both', padx=10, pady=10, ipadx=3, ipady=3)

def copy(s):
    window.clipboard_clear()
    window.clipboard_append(s)

Label(info_frame, text="Username:").grid(column=0, row=0)
username_entry = Entry(info_frame, width=25)
username_entry.grid(column=0, row=1, padx=5, pady=(10, 0))
copy_username = Button(info_frame, text="copy username", cursor="hand2", takefocus=False, width=25, command=lambda :copy(username_entry.get()))
copy_username.grid(column=0, row=2)

Label(info_frame, text="Url:").grid(column=1, row=0)
url_entry = Entry(info_frame, width=25)
url_entry.grid(column=1, row=1, padx=(0, 5), pady=(10, 0))
copy_url = Button(info_frame, text="copy url", cursor="hand2", takefocus=False, width=25, command=lambda :copy(url_entry.get()))
copy_url.grid(column=1, row=2, padx=(0, 5))

Label(info_frame, text="Password:").grid(column=2, row=0)
password_entry = Entry(info_frame, show="•", width=25)
password_entry.grid(column=2, row=1, padx=(0, 5), pady=(10, 0))
password_entry.bind("<Enter>", lambda a:password_entry.config(show=""))
password_entry.bind("<Leave>", lambda a:password_entry.config(show="•"))
copy_password = Button(info_frame, text="copy password", cursor="hand2", takefocus=False, width=25, command=lambda :copy(password_entry.get()))
copy_password.grid(column=2, row=2, padx=(0, 5))

Label(info_frame, text="History:").grid(column=3, row=0)
passwords_history_entry = Combobox(info_frame, width=25, show="•")
passwords_history_entry.grid(column=3, row=1, padx=(0, 5), pady=(10, 0))
passwords_history_entry.bind("<Enter>", lambda a:passwords_history_entry.config(show=""))
passwords_history_entry.bind("<Leave>", lambda a:passwords_history_entry.config(show="•"))
copy_history_password = Button(info_frame, text="copy password", cursor="hand2", takefocus=False, width=28, command=lambda :copy(passwords_history_entry.get()))
copy_history_password.grid(column=3, row=2, padx=(0, 5))

for column in columns:
    table.heading(column, text=column)
    table.column(column, anchor="center")

window.config(menu=menu)
window.mainloop()