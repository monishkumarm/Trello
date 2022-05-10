#!/bin/sh
echo "Ansible Entrypoint"

echo "[azure]" >> /hosts
echo "trellovm.southindia.cloudapp.azure.com" >> /hosts

echo "[all:vars]" >> /hosts
echo "ansible_connection=ssh" >> /hosts
echo "ansible_user= $SSH_USER" >> /hosts
echo "ansible_ssh_user= $SSH_USER" >> /hosts
echo "ansible_python_interpreter=/usr/bin/python3" >> /hosts
echo "ansible_ssh_private_key_file=/trellovm_key.pem" >> /hosts

echo "Entering the ansible using ansible-playbook"

ansible-playbook playbook.yml --user $SSH_USER