"Set compatibility to Vim only.
set nocompatible
set nolist
" show relative numbers
set rnu
" Helps force plug-ins to load correctly when it is turned back on below.
filetype off

" Turn on syntax highlighting.
syntax on

" For plug-ins to load correctly.
filetype plugin indent on

" Turn off modelines
set modelines=0

" Automatically wrap text that extends beyond the screen length.
set wrap
" Vim's auto indentation fature does not work properly with text copied from outside of Vim. Press the <F2> key to toggle paste mode on/off.
nnoremap <F2> :set invpaste paste?<CR>
imap <F2> <C-O>:set invpaste paste?<CR>
set pastetoggle=<F2>

" Uncomment below to set the max textwidth. Use a value corresponding to the width of your screen.
set textwidth=79
set formatoptions=tcqrn1
set tabstop=2
set shiftwidth=2
set softtabstop=2
set expandtab
set noshiftround

" Display 5 lines above/below the cursor when scrolling with a mouse.
set scrolloff=9
" Fixes common backspace problems
set backspace=indent,eol,start

" Speed up scrolling in Vim
set ttyfast

" Status bar
set laststatus=2

" Display options
set showmode
set showcmd

" Highlight matching pairs of brackets. Use the '%' character to jump between them.
set matchpairs+=<:>

" Show line numbers
set number

" Set status line display
set statusline=%F%m%r%h%w\ [FORMAT=%{&ff}]\ [TYPE=%Y]\ [POS=%l,%v][%p%%]\ [BUFFER=%n]\ %{strftime('%c')}

" Encoding
set encoding=utf-8

" Highlight matching search patterns
set hlsearch
" Enable incremental search
set incsearch
" Include matching uppercase words with lowercase search term
set ignorecase
" Include only uppercase words with uppercase search term
set smartcase

" Use ~x on an English Windows version or ~n for French.
au GUIEnter * simalt ~x 

" Store info from no more than 100 files at a time, 9999 lines of text, 100kb of data. Useful for copying large amounts of data between files.
set viminfo='100,<9999,s100

" Map the <Space> key to toggle a selected fold opened/closed.
nnoremap <silent> <Space> @=(foldlevel('.')?'za':"\<Space>")<CR>
vnoremap <Space> zf

" Automatically save and load folds
autocmd BufWinLeave *.* mkview
autocmd BufWinEnter *.* silent loadview"
let mapleader = " "
noremap <leader>w :w <cr>
noremap <leader>q :q<cr>
noremap <leader>wq :wq<cr>
noremap <leader>t :term<cr>
noremap <leader>tb :below term<cr>
noremap <leader>gs :CocSearch 
noremap <leader>fs :Files<cr>
" Managing buffers
noremap <leader>ls :ls<cr>:b
" For file tree, use m for menu option on the tree
nmap <leader>nt :NERDTreeFind<cr>
"For run Prettier, formating code
nmap <leader>p <Plug>(Prettier)<cr>

if empty(glob('~/.vim/autoload/plug.vim'))
  silent !curl -fLo ~/.vim/autoload/plug.vim --create-dirs
    \ https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim
  autocmd VimEnter * PlugInstall --sync | source $MYVIMRC
endif
call plug#begin('~/.vim/plugged')
Plug 'morhetz/gruvbox'     " Theme
Plug 'vim-airline/vim-airline'

" IDE
Plug 'junegunn/fzf', { 'do': { -> fzf#install() } } " For search files
Plug 'junegunn/fzf.vim'                             " For search files
Plug 'pangloss/vim-javascript'    " JavaScript support
Plug 'leafgarland/typescript-vim' " TypeScript syntax
Plug 'neoclide/coc.nvim' , { 'branch' : 'release' } " Code completion
Plug 'othree/html5.vim'   "  Code completion for html5
Plug 'ap/vim-css-color'    " For color preview 

Plug 'prettier/vim-prettier', { 'do': 'yarn install' }
Plug 'anyakichi/vim-surround'  " Change the surround of word, text etc. Use cs([

Plug 'scrooloose/nerdtree'        " For easy tree file navigation
Plug 'christoomey/vim-tmux-navigator' " For easy navigator between tree and editor
Plug 'Xuyuanp/nerdtree-git-plugin' " Show color file in the tree navigation

call plug#end()

let g:gruvbox_contrast_dark='hard'
" colorscheme darkblue
" colorscheme murphy
" colorscheme torte
colorscheme gruvbox

" For load prettier
" command! -nargs=0 Prettier :CocCommand prettier.formatFile

let g:coc_global_extensions = [ 
      \ 'coc-snippets',
      \ 'coc-pairs',
      \ 'coc-tsserver',
      \ 'coc-eslint',
      \ 'coc-json',
      \ ]

" nerdtree configuration for close tree afther select file
let NERDTreeQuitOnOpen=1

" Use <c-space> to trigger completion.
inoremap <silent><expr> <c-space> coc#refresh()

" ctrlp     this is for ignore the files that git ignore.
let g:ctrlp_user_command = ['.git/', 'git --git-dir=%s/.git ls-files -oc --exclude-standard']

" GoTo code navigation.
nmap <silent> gd <Plug>(coc-definition)
nmap <silent> gy <Plug>(coc-type-definition)
nmap <silent> gi <Plug>(coc-implementation)
nmap <silent> gr <Plug>(coc-references)
" Map the ii for Escape 
:imap ii <Esc>
