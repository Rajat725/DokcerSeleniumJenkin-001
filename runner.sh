echo "Checking if Selenium Grid is started or not ........ Fingers Crossed ..."

count=0  # Initialize count
while [ "$(curl -s http://${HUB:-selenium-hub}:4444/status | jq -r .value.ready)" != "true" ]; do
    count=$((count + 1))  # Increment count
    echo "Attempt ${count}"

    if [ "$count" -ge 30 ]; then  # Check if count exceeds the limit
        echo "**** HUB IS NOT READY WITHIN 30 SECONDS ****"
        exit 1
    fi

    sleep 1
done

echo "Selenium Grid is Setup properly , Running the tests ....."

#Starting command
java \
-Dseleniumgridenabled=${GRID:-true} \
-Dseleniumgridhub=${HUB:-selenium-hub} \
-cp 'libs/*' \
org.testng.TestNG \
test-suites/${XML:-AllTests.xml}