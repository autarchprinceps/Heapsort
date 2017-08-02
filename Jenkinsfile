pipeline {
  agent {
    node {
      label 'Cloud-Ubuntu-Slave'
    }
    
  }
  stages {
    stage('A') {
      steps {
        parallel(
          "A": {
            echo 'A'
            
          },
          "B": {
            echo 'B'
            
          }
        )
      }
    }
  }
}