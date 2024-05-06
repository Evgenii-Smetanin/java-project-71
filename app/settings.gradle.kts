classDirectories.setFrom(files(classDirectories.files.collect {
            fileTree(dir: app)
        }))

