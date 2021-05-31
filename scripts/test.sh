if [ -f ./breadgood-server/build/libs/breadgood-server-0.0.1-SNAPSHOT.jar ]; then
  echo "File Exist !!!";
fi

if [ ! -f ./breadgood-server/build/libs/breadgood-server-0.0.1-SNAPSHOT.jar ]; then
  echo "Not Exists";
fi
