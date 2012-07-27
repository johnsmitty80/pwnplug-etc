TARGETS = mountkernfs.sh udev mountdevsubfs.sh bootlogd hostname.sh hwclockfirst.sh checkroot.sh cryptdisks cryptdisks-early ifupdown ifupdown-clean mountall.sh mountoverflowtmp networking mountnfs.sh mountnfs-bootclean.sh urandom hwclock.sh fuse checkfs.sh pppd-dns bootmisc.sh mountall-bootclean.sh udev-mtab module-init-tools procps stop-bootlogd-single mtab.sh
INTERACTIVE = udev checkroot.sh cryptdisks cryptdisks-early checkfs.sh
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
bootlogd: mountdevsubfs.sh
hostname.sh: bootlogd
hwclockfirst.sh: mountdevsubfs.sh bootlogd
checkroot.sh: mountdevsubfs.sh hostname.sh hwclockfirst.sh bootlogd
cryptdisks: checkroot.sh cryptdisks-early udev
cryptdisks-early: checkroot.sh udev
ifupdown: ifupdown-clean
ifupdown-clean: checkroot.sh
mountall.sh: checkfs.sh
mountoverflowtmp: mountall-bootclean.sh
networking: mountkernfs.sh mountall.sh mountoverflowtmp ifupdown
mountnfs.sh: mountall.sh mountoverflowtmp networking ifupdown
mountnfs-bootclean.sh: mountall.sh mountoverflowtmp mountnfs.sh
urandom: mountall.sh mountoverflowtmp
hwclock.sh: checkroot.sh bootlogd
fuse: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh
checkfs.sh: cryptdisks checkroot.sh mtab.sh
pppd-dns: mountall.sh mountoverflowtmp
bootmisc.sh: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh udev
mountall-bootclean.sh: mountall.sh
udev-mtab: udev mountall.sh mountoverflowtmp
module-init-tools: checkroot.sh
procps: mountkernfs.sh mountall.sh mountoverflowtmp udev module-init-tools bootlogd
stop-bootlogd-single: mountall.sh mountoverflowtmp udev cryptdisks cryptdisks-early ifupdown ifupdown-clean networking mountnfs.sh mountnfs-bootclean.sh urandom hwclock.sh fuse checkroot.sh checkfs.sh mountkernfs.sh hostname.sh pppd-dns bootmisc.sh mountall-bootclean.sh mountdevsubfs.sh hwclockfirst.sh bootlogd udev-mtab module-init-tools procps mtab.sh
mtab.sh: checkroot.sh
