SUMMARY = "Magic Lantern Studio libutil utility library."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c71c8102293c2dce5947d7611f986063"

SRCREV = "ced026a568fb0b42b5612f0aab93ca3997e58ccc"
SRC_URI = "git://github.com/magic-lantern-studio/mle-core-util.git"

S = "${WORKDIR}/git"
#D = "${TMPDIR}/MagicLanternRoot"

SYSROOT_DIRS += "/opt"

# The base package, this includes everything needed to actually
# run the application on the target system.
FILES_${PN} += ""
# Development related files. Any headers, libraries and support
# files needed for development work on the target.
FILES_${PN}-dev += "\
    /opt/MagicLantern/lib/* \
    /opt/MagicLantern/include/mle/*"
FILES_${PN}-staticdev += "\
    /opt/MagicLantern/lib/*.a "

#inherit autotools

do_configure() {
    bbdebug 1 "Running libmlutil.bb custom do_configure()"
    bbdebug 1 "D =" ${D}
    export MLE_HOME=${S}
    export MLE_ROOT=${D}/opt/MagicLantern
    cd ${S}/util/linux/build
    libtoolize
    aclocal
    automake --add-missing
    autoconf
    ${S}/util/linux/build/configure --host=arm-poky-linux-gnueabi --prefix=$MLE_ROOT
}

do_compile() {
    bbdebug 1 "Running libmlutil.bb custom do_compile()"
    bbdebug 1 "D =" ${D}
    export MLE_HOME=${S}
    export MLE_ROOT=${D}/opt/MagicLantern
    cd ${S}/util/linux/build
    make
}

do_install_append() {
    bbdebug 1 "Running libmlutil.bb custom do_install_append()"
    bbdebug 1 "D =" ${D}
    export MLE_HOME=${S}
    export MLE_ROOT=${D}/opt/MagicLantern
    cd ${S}/util/linux/build
    make install
}
