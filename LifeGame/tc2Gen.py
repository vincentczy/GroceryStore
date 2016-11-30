

tc = open('testcase2.txt','w')
for x in range(0,40):
	text = "%d %d\n" % (x, x)
	tc.write(text)
	text = "%d %d\n" % (x, 39 - x)
	tc.write(text)
	tc.write("-1 -1\n")
	tc.write("10")