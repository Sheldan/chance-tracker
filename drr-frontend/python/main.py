import logging
import os

import requests
from functools import wraps
from flask import Flask, render_template, request

FORMAT = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
logging.basicConfig(encoding='utf-8', level=logging.INFO, format=FORMAT)
template_dir = os.path.abspath('resources')
app = Flask(__name__, static_url_path='', static_folder=template_dir, template_folder=template_dir)

backend_host = os.getenv('BACKEND_HOST')
backend_port = os.getenv('BACKEND_PORT')
base_url = f'http://{backend_host}:{backend_port}'
inventory_url = f'{base_url}/inventory'
shared_inventory_url = f'{base_url}/sharedInventory'
wallet_url = f'{base_url}/wallet'
bank_url = f'{base_url}/bank'
materials_url = f'{base_url}/materials'
characters_url = f'{base_url}/characters'

api_key_header_name = 'gw2-api-key'


def api_key_required(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if request.headers.get(api_key_header_name) is None:
            return f'Provide api key with header "{api_key_header_name}"', 400
        return f(*args, **kwargs)
    return decorated_function


@app.route('/health')
def health():
    return 'OK', 200


@app.route('/drr')
def drr_serving():
    return render_template('drr/index.html')


@app.route('/characters/')
@api_key_required
def get_account_characters():
    api_key = request.headers.get(api_key_header_name)
    headers = {'api-key': api_key}
    logging.info("Loading characters")
    inventory_response = requests.get(f'{characters_url}', headers=headers)
    return inventory_response.text


@app.route('/inventory/')
@api_key_required
def get_account_inventory():
    api_key = request.headers.get(api_key_header_name)
    headers = {'api-key': api_key}
    logging.info("Rendering inventory of full account")
    inventory_response = requests.get(f'{inventory_url}', headers=headers)
    text = inventory_response.text
    return text


@app.route('/wallet/')
@api_key_required
def get_account_wallet():
    api_key = request.headers.get(api_key_header_name)
    headers = {'api-key': api_key}
    logging.info("Rendering wallet of account")
    wallet_response = requests.get(f'{wallet_url}', headers=headers)
    return wallet_response.text


@app.route('/bank/')
@api_key_required
def get_account_bank():
    api_key = request.headers.get(api_key_header_name)
    headers = {'api-key': api_key}
    logging.info("Rendering bank of account")
    bank_response = requests.get(f'{bank_url}', headers=headers)
    return bank_response.text


@app.route('/materials/')
@api_key_required
def get_materials():
    api_key = request.headers.get(api_key_header_name)
    headers = {'api-key': api_key}
    logging.info("Rendering materials of account")
    bank_response = requests.get(f'{materials_url}', headers=headers)
    return bank_response.text


@app.route('/sharedInventory/')
@api_key_required
def get_shared_inventory():
    api_key = request.headers.get(api_key_header_name)
    headers = {'api-key': api_key}
    logging.info("Rendering shared inventory of account")
    bank_response = requests.get(f'{shared_inventory_url}', headers=headers)
    return bank_response.text


@app.route('/inventory/<character_name>/')
@api_key_required
def get_character_inventory(character_name):
    api_key = request.headers.get(api_key_header_name)
    headers = {'api-key': api_key}
    logging.info("rendering inventory of individual character.")
    inventory_response = requests.get(f'{inventory_url}/{character_name}', headers=headers)
    return inventory_response.text


if __name__ == "__main__":
    from waitress import serve

    serve(app, host="0.0.0.0", port=8080)
