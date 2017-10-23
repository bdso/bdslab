#!/bin/bash
storm nimbus &

storm supervisor &

storm ui &