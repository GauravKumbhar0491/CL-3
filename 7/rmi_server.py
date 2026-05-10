import Pyro4


@Pyro4.expose
class StringConcatenator:

    def concatenate(self, str1, str2):
        print("string 1: ", str1)
        print("string 2: ", str2)
        return str1 + str2


def start_server():

    concatenator = StringConcatenator()

    daemon = Pyro4.Daemon()
    uri = daemon.register(concatenator)

    print("Server URI:", uri)
    print("Server is ready...")

    daemon.requestLoop()


if __name__ == "__main__":
    start_server()