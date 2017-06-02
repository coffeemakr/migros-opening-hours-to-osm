[![Build Status](https://travis-ci.org/coffeemakr/migrosm.svg?branch=master)](https://travis-ci.org/coffeemakr/migrosm)

# MigrOSM
Converter from Migros opening hours to the Openstreetmap


## Market Loader

The market loader is a command line tool to load markets from the API and print them in different formats.

### Installation

Download the current version from [Github](https://github.com/coffeemakr/migrosm/releases) and extract it.

### Example

For example you could use the following command load all Alnatura stores in GeoJSON format 
```
market-loader alnatura -f geojson 
```