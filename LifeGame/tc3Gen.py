tc = open('testcase3.txt','w')
for x in range(0,20):
	for y in range(0,40):
		text = "%d %d\n" % (2*x, y)
		tc.write(text)
tc.write("-1 -1\n")
tc.write("100")