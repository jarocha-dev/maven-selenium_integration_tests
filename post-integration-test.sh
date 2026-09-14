#!/bin/bash

set -euxo pipefail

SCRIPT_DIR=$(dirname $(readlink -f $0))

export PATH=$SCRIPT_DIR/target/mysql-dist/bin:$PATH

mysql --version


