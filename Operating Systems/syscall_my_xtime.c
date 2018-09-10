#include <asm/uaccess.h>
#include <linux/printk.h>
#include <linux/slab.h>
asmlinkage int sys_my_xtime(struct timespec *current_time){
// printk(KERN_ALERT "Hello World!\n");
// return 0;

/*	https://www.ibm.com/developerworks/library/l-kernel-memory-access/index.html 
* 	reference for checking access to user space from kernel
*	below is for REFERENCE
*
*	access_ok( type, addr, size );
*	struct timespec {
*        time_t  tv_sec;
*        long    tv_nsec;
*	};
*/

struct timespec local = current_kernel_time();

bool access_to_userspace = access_ok(VERIFY_WRITE, current_time, sizeof(struct timespec));

if(!access_to_userspace)
    return -EFAULT; 	//return EFAULT when the user space is not valid and not writable

current_time->tv_sec = local.tv_sec;
current_time->tv_nsec = local.tv_nsec;

printk(KERN_ALERT "Time from my_xtime syscall: %ld", current_time->tv_nsec);

return 0;
}
EXPORT_SYMBOL(sys_my_xtime);