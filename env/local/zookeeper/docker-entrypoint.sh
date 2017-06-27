#!/bin/bash
set -e
exec zkServer.sh start-foreground
exec "$@"