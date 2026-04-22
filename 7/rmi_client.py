"""
   Practical_2 - Design a distributed application using RMI for remote computation where client submits two strings to
                 the server and server returns the concatenation of the given strings."""

import Pyro4

def main():
    url = input("Enter server's url: ")

    concatenator = Pyro4.Proxy(url)

    str1 = input("Enter string 1: ")
    str2 = input("enter string 2: ")

    result = concatenator.concatenate(str1,str2)

    print("Concatenated String is: ",result)

if __name__ == "__main__":
    main()