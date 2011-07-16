eventCompileStart = {
    ant.delete(file: "${grailsSettings.classesDir}/bigList")
}

eventCompileEnd = {
    //println "${System.getProperty('bigList')}"
    if ('true'.equals(System.getProperty('bigList', 'false'))) {
        ant.touch(file: "${grailsSettings.classesDir}/bigList")
    }
}

