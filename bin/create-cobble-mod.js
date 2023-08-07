#!/usr/bin/env node

const { create } = require('../dist/lib');
const args = process.argv.slice(2);
create(process.cwd(), args);