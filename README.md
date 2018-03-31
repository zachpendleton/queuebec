# Queuebec

Ever wanted a job queue that didn't ask you to give up the creature comforts
of your web stack? Probably not, but here one is anyway.

## Usage

Make any HTTP request to the web server that is started on port 3001. That
request will be submitted to the job runner and, if it matches a registered
job, will execute.

## License

Copyright © 2018 Zach Pendleton

Distributed under the MIT license.