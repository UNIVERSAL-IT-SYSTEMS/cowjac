Cowjac v0.x
===========
                                  
Copyright (C) 2012 David Given

2012-07-15
            
            
                                 
Introduction
------------

cowjac is an experimental AOT Java compiler. It works by compiling Java into
reasonably portable C++, and then compiling the C++.

It's intended for use on devices which don't support or allow running a JVM.
cowjac produces completely standalone binaries.

Performance is adequate (some very rough-and-ready benchmarks on my machine
show that, with all the optimisation turned on, it runs at about a quarter the
speed of Hotspot Server... which is still very fast). Footprint is not
brilliant; right now every binary gets most of the Apache Harmony standard
library compiled into it, which means that they're about 2MB minimum.

Reflection is not supported. There's some basic and largely broken support for
Class objects, and just enough of java.lang.reflect to make System.arraycopy
work, but that's it. There is currently no garbage collector or threading (but
both are planned; all the hooks are in place for a simple and portable
mark/sweep garbage collector, but it's not done yet).

The current status of cowjac is that it will run command-line applications well
enough to do benchmarks.

cowjac uses the Soot Java analysis framework to do the heavy lifting. It also
contains a severely truncated version of the Apache Harmony standard library.



Setup
-----

To build cowjac, you will need Java, Scala, and ant.

Edit the build.xml and set up the two scala property paths at the top, and then
do:

    ant run

This should compile the cowjac compiler and then run it, translating the
contents of library/src into C++.

Once this has done you should be able to build it:

    make

...and run it:

    ./cowjac



More information
----------------

Please see the stub website at:

    http://cowlark.com/cowjac

...for more information.



The author
----------

Cowjac was written by me, David Given. You may contact me at dg@cowlark.com, or
visit my website at http://www.cowlark.com. There may or may not be anything
interesting there.



Licensing
---------

The Soot analysis framework is licensed under the LGPL-3. See COPYING.soot for
the full text.

The Apache Harmony library is licensed under the Apache v2.0 license. See
COPYING.harmony for the full text.

Everything else is licensed under the Simplified BSD license. See
COPYING.cowjac for the full text.

