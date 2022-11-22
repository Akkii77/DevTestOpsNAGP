pipeline{
  agent any
  tools{
      maven "Maven"
  }
  stages{
      stage('checkout')
      {
        steps
        {
          checkout scm
        }
      }
       stage('Build')
      {
        steps
        {
          bat "mvn clean"
        }
      }
       stage('Unit Testing')
      {
        steps
        {
          bat "mvn test"
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
