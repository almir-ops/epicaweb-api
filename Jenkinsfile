node {
    def currentBuild = currentBuild
    def currentJob = currentBuild.rawBuild.getParent()
    def currentJobName = currentJob.name
    def currentJobExecutor = currentJob.executor
    def currentBuildNumber = currentBuild.rawBuild.getNumber()
    def queue = currentJob.queue

    // Check if there's another build of the same job running
    if (currentJobExecutor) {
        echo "Build is already in progress."
        // Cancel the build that is in the queue
        queue.items.each { item ->
            if (item.task.name == currentJobName && item.id != currentBuildNumber) {
                echo "Cancelling queued build: ${item.id}"
                item.cancel()
            }
        }
    } else {
        // Build logic
        echo "Starting build."
        //...
    }
}
