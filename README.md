# Example Mina SFTP serving Google Cloud Storage
This just a minimalistic example of a sftpd serving a [Google Cloud Storage Bucket](https://cloud.google.com/storage) using [Apache Mina SFTP](https://github.com/apache/mina-sshd) and the [Google Storage Nio (Java)](https://github.com/googleapis/java-storage-nio).

This really simple way of doing it became possible after a few small fixes were implemented by me in the Google Storage Nio (available since 0.126.0).

Disclaimer: This is JUST an insecure example showing only the basics needed to get it running.

=======

    Copyright (C) 2022 Niels Basjes

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
