CXXFLAGS = \
	-ffunction-sections \
	-fdata-sections \
	-g \
	-I cowjacOutput \
	-I library/include
	
SRCS = $(wildcard cowjacOutput/*.cc)
OBJS = $(patsubst %.cc, %.o, $(SRCS))

all: $(OBJS)

%.o: %.cc
	clang $(CXXFLAGS) -c '$<' -o '$@'
	