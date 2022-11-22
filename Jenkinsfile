pipeline{
  agent any
  tools{
      maven "Maven"
  }
  stages{
      stage("checkout")
      {
        steps
        {
          checkout scm
        }
      }
       stage("Build")
      {
        steps
        {
          sh "echo build"
        }
        
      }
       stage('Unit Testing')
      {
        steps
        {
          sh "echo Test"
        }
      }
       stage('Sonar Analysis')
      {
        steps
        {
          withSonarQubeEnv("Test _Sonar")
          {
            sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar"
          }
        }
      }
  }
}
