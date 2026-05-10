"""Design a distributed application using RMI for remote computation where client submits two
strings to the server and server returns the concatenation of the given strings."""

import Pyro4

uri = input("Enter Server URI: ")

server = Pyro4.Proxy(uri)

s1 = input("Enter first string: ")
s2 = input("Enter second string: ")

result = server.concatenate(s1, s2)

print("Concatenated String:", result)