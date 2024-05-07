const version: string | undefined = Deno.env.get('VERSION');


const args = Deno.args[0];
console.log(`args is : ` + args) ;

if (version) {
 const versionNo = version.replace("/refs/tags/", "")
console.log(`output is: ${versionNo} ... type: ${typeof versionNo}`);
console.log(`version: ` + versionNo.replace("/refs/tags/", ""))

console.log(`current directory is : ${Deno.cwd()}`);


let readmeContent: string = Deno.readTextFileSync('readme.md');

readmeContent = readmeContent.replace(/:addjs:v\[\d\]+\.\[\d\]+/, `:addjs:${version}`) ;
Deno.writeTextFileSync('readme.md', readmeContent);
}
