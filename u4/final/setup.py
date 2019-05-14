from distutils.core import setup, Extension, DEBUG

module1 = Extension(
    'hectorASM',
    sources = ['hectorASMmodule.c']
)

setup (
    name = 'hectorASM',
    version = '1.0',
    description = 'My own ASM functions for python',
    author = 'hectorrdz98',
    url = 'http://sasukector.com',
    ext_modules = [module1]
)