# Queuebec

Ever wanted a job queue that didn't ask you to give up the creature comforts
of your web stack? Probably not, but here one is anyway.

## Getting started

First, you'll need [Leiningen](https://leiningen.org) installed (instructions
on the Leiningen site).

Next, execute `lein run`.

Last, make an HTTP call to the service:

`curl http://localhost:3001/sum -d'x=27&y=15'`

You'll see the web server log `42` as the job executes.

## TODO

A whole lot.

* Failure handling
* Retries
* Priority
* Job middleware
* Distributed job storage

## License

Copyright © 2018 Zach Pendleton

Distributed under the MIT license.
