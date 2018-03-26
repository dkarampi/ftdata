awk -F , '$3 == "\"FOOBAR\"" && $7 != ""' myfile.csv

while IFS="," read _ aaa _ bbb; do
	echo $aaa
done < <(grep "^FOOBAR" $param)
