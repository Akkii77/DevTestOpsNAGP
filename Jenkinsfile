pipeline{
  agent any
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
       
  }
}
