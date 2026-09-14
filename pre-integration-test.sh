#!/bin/bash

set -euxo pipefail

SCRIPT_DIR=$(dirname $(readlink -f $0))

export PATH=$SCRIPT_DIR/target/mysql-dist/bin:$PATH

mysql --version
cat << EOF | mysql --host=127.0.0.1 --user=root --password=root
CREATE DATABASE my_app;
EOF
