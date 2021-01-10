node("linux") {
    CMD = "curl --write-out %{http_code} --silent --output /dev/null localhost:80"
    customImage = ""
    stage("Clone repo"){
        checkout scm
    }
    stage("Build image") {
        customImage = docker.build("dvdrbn/opsschool-jenkins-test-01")
    }
    stage("Test container") {
        docker.image(customImage).withRun{
            timeout(10){
                waitUntil{
                    def responseGetAll = httpRequest consoleLogResponseBody: true,
                        contentType: 'APPLICATION_JSON',
                        httpMode: 'GET',
                        url: "http://localhost:80/",
                        validResponseCodes: '200'
                }
            }
        }
    }
}
