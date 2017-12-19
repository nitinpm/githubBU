#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/cdev.h>
#include <asm/uaccess.h>
#include <linux/proc_fs.h>
#include <linux/printk.h>
#include <linux/slab.h>
#define DEVICE_NAME "mytime"
#define SIZE 100
MODULE_LICENSE("GPL");

static int error_count=0;
//static struct timespec time;
//static struct timespec getnstime;
//static char time_inchar[256];
//static int Major;		/* Major number assigned to our device driver */

module_param(intBuffSize, int, S_IRUSR | S_IWUSR);

MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("Character Device for PC problem");
MODULE_AUTHOR("Nitin Mahadik");

char  *devbuffer;


struct semaphore empty;		// to keep track of empty integer buffer slots
struct semaphore full;			//to keep track of full integer buffer slots
struct semaphore mutex;		//mutex for critical region


int init_module(void);
void cleanup_module(void);
void unregister_device(void);
static int device_open(struct inode *, struct file *);
static ssize_t device_read(struct file *, char *, size_t, loff_t *);



static struct file_operations fops = {
	.read = device_read,
	.open = device_open,
	.write = device_write,
	.release = device_release,
	.owner = THIS_MODULE
};



static int device_open(struct inode *inode, struct file *filep)
{
        printk(KERN_INFO "MycharDevice opened");
        return 0;
}





// called when module is installed
int __init init_module()
{
	int check;
	
	printk(KERN_ALERT "Hello!! mytime initiated...\n");
	mytime.name = "nmpipe";
	mytime.minor = MISC_DYNAMIC_MINOR;
	mytime.fops = &fops;
	
	devbuffer = kmalloc(intBuffSize, GFP_KERNEL);
	
	check = misc_register(&my_dev);
	
	//Major = register_chrdev(0, DEVICE_NAME, &fops);
	if (!devbuffer){
	printk(KERN_ALERT "Error: Not able to allocate memory using kmalloc\n");
    }

	return 0;
}




static ssize_t device_read(struct file *filep,char *userBuffer,size_t count,loff_t *offset)
{
    //time =  current_kernel_time();
	//getnstimeofday(&getnstime);

	//sprintf(time_inchar, "current_kernel_time: %ld\t%ld\ngetnstimeofday: %ld\t%ld", time.tv_sec ,time.tv_nsec, getnstime.tv_sec, getnstime.tv_nsec);
	
	//error_count=copy_to_user(userInt,time_inchar,sizeof(time_inchar));

	error_count = copy_to_user(userBuffer, devbuffer, 4);
        if(error_count==0)
        {
                printk(KERN_INFO "Time Copied to User successfully");
                return 0;

        }
 	else
        {
                printk(KERN_INFO "Time Copy failed %d characters to User",error_count);
                return -EFAULT;
        }

}


ssize_t device_write(struct file *filp,const char *userBuffer,size_t len,loff_t *off)
{
	error_count = copy_from_user(devbuffer, userBuffer, 4); 

if(error_count==0)
        {
                printk(KERN_INFO "Time Copied to User successfully");
                return 0;

        }
 	else
        {
                printk(KERN_INFO "Time Copy failed %d characters to User",error_count);
                return -EFAULT;
        }	
	
	
	
}





// called when module is removed
void __exit cleanup_module()
{
	printk(KERN_ALERT "Goodbye, cruel world!!\n");
	//time =  current_kernel_time();
	//printk(KERN_ALERT "\nTime during exit: %ld\t%ld",time.tv_sec,time.tv_nsec);
	kfree(devbuffer);
	unregister_device();
}


void unregister_device(void)
{
    printk( KERN_NOTICE "NM: Unregister mytime is in progress..." );
    if(Major != 0)
    {
        unregister_chrdev(Major, DEVICE_NAME);
    }
}
