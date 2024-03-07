rootProject.name = "minimarket"

include("platform:api-gateway")
include("platform:eureka-server")

include("service:member")
include("service:product")
include("service:order")
include("service:payment")
