#!/bin/sh

unset LANG
unset LC_CTYPE
unset LC_TIME
version=$1
buildDirectory=$2
user=`whoami`
date=`date`
dir=`pwd`
cwd=`dirname $dir`
if [ -d ../.svn ]; then
  revision=`svn info ../ | sed -n -e 's/Last Changed Rev: \(.*\)/\1/p'`
  url=`svn info  ../ | sed -n -e 's/URL: \(.*\)/\1/p'`
  branch=`echo $url | sed -n -e 's,.*\(branches/.*\)$,\1,p' \
                             -e 's,.*\(tags/.*\)$,\1,p' \
                             -e 's,.*trunk$,trunk,p'`
elif git rev-parse HEAD 2>/dev/null > /dev/null ; then
  revision=`git log -1 --pretty=format:"%H"`
  hostname=`hostname`
  branch=`git branch | sed -n -e 's/^* //p'`
  url="git://${hostname}${cwd}"
else
  revision="Unknown"
  branch="Unknown"
  url="file://$cwd"
fi

if [ -n "$(which md5sum)" ]; then
  srcChecksum=`find ../ -name '*.java' | grep -v generated-sources | LC_ALL=C sort | \
      xargs md5sum | md5sum | cut -d ' ' -f 1`
else
  srcChecksum=`find ../ -name '*.java' | grep -v generated-sources | LC_ALL=C sort | \
      xargs md5 | md5 | cut -d ' ' -f 1`
fi

mkdir -p $buildDirectory/generated-sources/java/com/hikvision/mdp/
cat << EOF | \
  sed -e "s/VERSION/$version/" -e "s/USER/$user/" -e "s/DATE/$date/" \
      -e "s|URL|$url|" -e "s/REV/$revision/" \
      -e "s|BRANCH|$branch|" -e "s/SRCCHECKSUM/$srcChecksum/" \
      > $buildDirectory/generated-sources/java/com/hikvision/mdp/package-info.java
/*
 * Generated by scripts/saveVersion.sh
 */
@VersionAnnotation(version="VERSION", revision="REV", branch="BRANCH",
                         user="USER", date="DATE", url="URL",
                         srcChecksum="SRCCHECKSUM")
package com.hikvision.mdp;
EOF