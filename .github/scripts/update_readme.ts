import { readFileSync, writeFileSync } from 'https://deno.land/std/fs/mod.ts';

const version: string | undefined = Deno.env.get('VERSION');

if (!version) {
    throw new Error('VERSION environment variable not found.');
}

let readmeContent: string = readFileSync('README.md', { encoding: 'utf8' });

readmeContent = readmeContent.replace(/Current version: .*/, `Current version: ${version}`);

writeFileSync('README.md', readmeContent, { encoding: 'utf8' });

