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
            timeout(time: 15, unit: 'SECONDS') {
                waitUntil{
                    try {         
                        sh "curl -s --head  --request GET  localhost:80 | grep '200'"
                        return true
                    } catch (Exception e) {
                        return false
                  }
                }
            }
        }
    }
}
