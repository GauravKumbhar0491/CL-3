"""
   Practical_2 - Design a distributed application using RMI for remote computation where client submits two strings to
                 the server and server returns the concatenation of the given strings."""


import Pyro4

class  StringConcatenator:
    @Pyro4.expose
    def concatenate(self,str1,str2):
        return str1+str2
    
def start_sever():
    conacatenator =StringConcatenator()

    daemon = Pyro4.Daemon()
    url = daemon.register(conacatenator)

    print("Server URL: ",url)

    print("Server is ready.")
    daemon.requestLoop()


if __name__ == '__main__':
    start_sever()