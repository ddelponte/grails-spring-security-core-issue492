package issue492latest

class BootStrap {
    def readConfigService

    def init = { servletContext ->
        readConfigService.serviceMethod()
    }
    def destroy = {
    }
}
